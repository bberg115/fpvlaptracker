/* global moment */
angular.module('setup', ['ngDialog', 'ngProgress', 'ui.bootstrap']).controller('setup', function (
        $scope, ngDialog, $interval, $location, ngProgressFactory, SetupService, Alerts, Util, LoginService
        ) {

    $scope.chipid = parseInt($location.search().chipid);

    $scope.frequencies = [
        5865, 5845, 5825, 5805, 5785, 5765, 5745, 5725, // Band A
        5733, 5752, 5771, 5790, 5809, 5828, 5847, 5866, // Band B
        5705, 5685, 5665, 5645, 5885, 5905, 5925, 5945, // Band E
        5740, 5760, 5780, 5800, 5820, 5840, 5860, 5880, // Band F / Airwave
        5658, 5695, 5732, 5769, 5806, 5843, 5880, 5917, // Band C / Immersion Raceband
        5362, 5399, 5436, 5473, 5510, 5547, 5584, 5621  // Band D / 5.3    
    ];
    $scope.bands = [
        "A1 5865 MHz (Boscam, TBS)", "A2 5845 MHz (Boscam, TBS)", "A3 5825 MHz (Boscam, TBS)", "A4 5805 MHz (Boscam, TBS)", "A5 5785 MHz (Boscam, TBS)", "A6 5765 MHz (Boscam, TBS)", "A7 5745 MHz (Boscam, TBS)", "A8 5725 MHz (Boscam, TBS)",
        "B1 5733 MHz (Boscam)", "B2 5752 MHz (Boscam)", "B3 5771 MHz (Boscam)", "B4 5790 MHz (Boscam)", "B5 5809 MHz (Boscam)", "B6 5828 MHz (Boscam)", "B7 5847 MHz (Boscam)", "B8 5866 MHz (Boscam)",
        "E1 5705 MHz (Boscam)", "E2 5685 MHz (Boscam)", "E3 5665 MHz (Boscam)", "E4 5645 MHz (Boscam)", "E5 5885 MHz (Boscam)", "E6 5905 MHz (Boscam)", "E7 5925 MHz (Boscam)", "E8 5945 MHz (Boscam)",
        "F1 5740 MHz (Airwave, Fatshark, ImmersionRC)", "F2 5760 MHz (Airwave, Fatshark, ImmersionRC)", "F3 5780 MHz (Airwave, Fatshark, ImmersionRC)", "F4 5800 MHz (Airwave, Fatshark, ImmersionRC)", "F5 5820 MHz (Airwave, Fatshark, ImmersionRC)", "F6 MHz 5840 (Airwave, Fatshark, ImmersionRC)", "F7 MHz 5860 (Airwave, Fatshark, ImmersionRC)", "F8 5880 MHz (Airwave, Fatshark, ImmersionRC)",
        "R1 5658 MHz (Raceband)", "R2 5695 MHz (Raceband)", "R3 5732 MHz (Raceband)", "R4 5769 MHz (Raceband)", "R5 5806 MHz (Raceband)", "R6 5843 MHz (Raceband)", "R7 5880 MHz (Raceband)", "R8 5917 MHz (Raceband)",
        "L1 5362 MHz (Boscam)", "L2 5399 MHz (Boscam)", "L3 5436 MHz (Boscam)", "L4 5473 MHz (Boscam)", "L5 5510 MHz (Boscam)", "L6 5547 MHz (Boscam)", "L7 5584 MHz (Boscam)", "L8 5621 MHz (Boscam)"
    ];

    $scope.deviceData = {
        frequency: 0,
        minimumLapTime: 0,
        triggerThreshold: 0,
        triggerThresholdCalibration: 0,
        calibrationOffset: 0,
        state: "unknown",
        triggerValue: 0,
        voltage: 0.0,
        uptime: 0,
        defaultVref: 0,
        rssi: 0,
        loopTime: 0,
        filterRatio: 0.0,
        filterRatioCalibration: 0.0,
        version: "",
        participantName: "-",
        ipAddress: ""
    };

    $scope.frequencyTable = [];
    $scope.promise = null;
    $scope.loadingDeviceData = false;
    $scope.alerts = Alerts;
    $scope.cells = 1;

    let getInitialValue = function (frequency) {
        frequency = parseInt(frequency);
        let ret = null;
        $scope.frequencyTable.forEach(function (value) {
            if (value.frequency === frequency) {
                ret = value;
            }
        });
        if (ret !== null) {
            return ret;
        }
        console.log("using default");
        return $scope.frequencyTable[0];
    };

    // build table of frequencies with names
    $scope.frequencies.forEach(function (value) {
        let index = $scope.frequencies.indexOf(value);
        let selectedBand = $scope.bands[index];
        $scope.frequencyTable.push({
            frequency: value,
            name: selectedBand
        });
    });

    $scope.progressbar = ngProgressFactory.createInstance();
    console.log("chipid", $scope.chipid);

    $scope.rebootDevice = function() {
        SetupService.rebootDevice($scope.chipid)
            .then(function (response) {
                if (response.status === "NOK") {
                    ngDialog.open({template: 'dataFailure', scope: $scope, data: {message: "failed to reboot device"}});
                } else {
                    ngDialog.open({template: 'reboot', scope: $scope});
                    $location.path('/');
                }
            })
            .catch(function (response) {
                ngDialog.open({template: 'dataFailure', scope: $scope, data: {message: "failed to reboot device"}});
            })
            .finally(function () {
                $scope.progressbar.complete();
                Util.displayOverlay(false);
            });
    };

    $scope.loadDeviceData = function (overlay) {
        if (overlay === undefined || overlay === null) {
            overlay = true;
        }
        if (!$scope.loadingDeviceData) {
            $scope.progressbar.start();
            if (overlay) {
                Util.displayOverlay(true);
            }
            $scope.loadingDeviceData = true;
            SetupService.loadDeviceData($scope.chipid)
                    .then(function (response) {
                        $scope.deviceData.minimumLapTime = parseInt(response.minimumLapTime / 1000);
                        $scope.deviceData.triggerThreshold = response.triggerThreshold;
                        $scope.deviceData.triggerThresholdCalibration = response.triggerThresholdCalibration;
                        $scope.deviceData.calibrationOffset = response.calibrationOffset;
                        $scope.deviceData.state = response.state;
                        $scope.deviceData.triggerValue = response.triggerValue;
                        $scope.deviceData.voltage = response.voltage;
                        $scope.deviceData.uptime = response.uptime;
                        $scope.deviceData.defaultVref = response.defaultVref;
                        $scope.deviceData.rssi = response.rssi;
                        $scope.deviceData.loopTime = response.loopTime;
                        $scope.deviceData.filterRatio = response.filterRatio;
                        $scope.deviceData.filterRatioCalibration = response.filterRatioCalibration;
                        $scope.deviceData.version = response.version;
                        $scope.deviceData.ipAddress = response.ipAddress;
                        $scope.deviceData.frequency = getInitialValue(response.frequency);
                        $scope.deviceData.participantName = response.participantName;
                        console.log($scope.deviceData);
                        
                        if ($scope.deviceData.voltage > 7 && $scope.deviceData.voltage <= 10) {
                            $scope.cells = 2;
                        } else if ($scope.deviceData.voltage > 10 && $scope.deviceData.voltage <= 13) {
                            $scope.cells = 3;
                        } else if ($scope.deviceData.voltage > 13 && $scope.deviceData.voltage <= 17) {
                            $scope.cells = 4;
                        } else if ($scope.deviceData.voltage > 17 && $scope.deviceData.voltage <= 21.5) {
                            $scope.cells = 5;
                        } else if ($scope.deviceData.voltage > 21.5 && $scope.deviceData.voltage <= 26) {
                            $scope.cells = 6;
                        }
                    })
                    .catch(function (response) {
                        ngDialog.open({template: 'dataFailure', scope: $scope, data: {message: "failed to load device data"}});
                        $scope.deviceData.frequency = 0;
                        $scope.deviceData.minimumLapTime = 0;
                        $scope.deviceData.triggerThreshold = 0;
                        $scope.deviceData.triggerThresholdCalibration = 0;
                        $scope.deviceData.calibrationOffset = 0;
                        $scope.deviceData.state = "unknown";
                        $scope.deviceData.triggerValue = 0;
                        $scope.deviceData.voltage = 0.0;
                        $scope.deviceData.uptime = 0;
                        $scope.deviceData.defaultVref = 0;
                        $scope.deviceData.rssi = 0;
                        $scope.deviceData.loopTime = 0;
                        $scope.deviceData.filterRatio = 0.0;
                        $scope.deviceData.filterRatioCalibration = 0.0;
                        $scope.deviceData.version = "unknown";
                        $scope.deviceData.ipAddress = "0.0.0.0";
                        $scope.deviceData.participantName = "-";
                    })
                    .finally(function () {
                        $scope.loadingDeviceData = false;
                        $scope.progressbar.complete();
                        Util.displayOverlay(false);
                    });
        }
    };
    
    $scope.$on('$destroy', function () {
        if (angular.isDefined($scope.promise)) {
            $interval.cancel($scope.promise);
            $scope.promise = undefined;
        }
    });

    $scope.saveDeviceData = function () {
        $scope.progressbar.start();
        Util.displayOverlay(true);
        console.log("before save", $scope.deviceData);
        SetupService.saveDeviceData($scope.chipid, $scope.deviceData)
                .then(function (response) {
                    Alerts.addSuccess();
                    console.log(response);
                    if (response.status === "OK reboot") {
                        $scope.rebootDevice();
                    } else {
                        $scope.loadDeviceData();
                    }
                })
                .catch(function (response) {
                    ngDialog.open({template: 'failedSave', scope: $scope});
                })
                .finally(function () {
                    $scope.progressbar.complete();
                    Util.displayOverlay(false);
                });
    };

    $scope.loadDeviceData();

}).factory('SetupService', function ($http) {
    var factory = {};

    factory.loadDeviceData = function (chipid) {
        return $http.get("/api/participant/deviceData", {params: {chipid: chipid}, timeout: 10000}).then(function (response) {
            return response.data;
        });
    };

    factory.rebootDevice = function (chipid) {
        return $http.get("/api/auth/participant/reboot", {params: {chipid: chipid}, timeout: 10000}).then(function (response) {
            return response.data;
        });
    };

    factory.saveDeviceData = function (chipid, deviceData) {
        console.log("in save", deviceData);
        var deviceData2 = deviceData;
        deviceData2.chipid = chipid;
        deviceData2.minimumLapTime *= 1000;
        deviceData2.frequency = deviceData2.frequency.frequency;
        console.log("in save2", deviceData2);
        return $http.post("/api/auth/participant/deviceData", deviceData2, {timeout: 10000}, null).then(function (response) {
            return response.data;
        });
    };
    return factory;
});
